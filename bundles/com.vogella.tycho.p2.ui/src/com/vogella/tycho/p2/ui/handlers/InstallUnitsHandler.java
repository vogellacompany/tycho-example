
package com.vogella.tycho.p2.ui.handlers;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.InstallOperation;
import org.eclipse.equinox.p2.operations.ProfileChangeOperation;
import org.eclipse.equinox.p2.operations.ProvisioningJob;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;

public class InstallUnitsHandler {
	
	URI location = URI.create(
			"http://localhost:8081/nexus/service/local/repositories/master.group.unzip/content/com/vogella/tycho/com.vogella.tycho.update/1.0.0-SNAPSHOT/com.vogella.tycho.update-1.0.0-SNAPSHOT.zip-unzip");
	
	@Execute
	public void execute(IProvisioningAgent agent) {
		Job installJob = Job.create("Search for new IUs", new ICoreRunnable() {

			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				installNewUnits(agent, monitor);
			}

		});
		installJob.schedule();
	}

	private void installNewUnits(IProvisioningAgent agent, IProgressMonitor monitor) throws ProvisionException {
		SubMonitor subMonitor = SubMonitor.convert(monitor);
		IMetadataRepositoryManager manager = (IMetadataRepositoryManager) agent
				.getService(IMetadataRepositoryManager.SERVICE_NAME);
		
		IMetadataRepository repository = manager.loadRepository(location, subMonitor.split(1));
		IQueryResult<IInstallableUnit> queryResult = repository.query(QueryUtil.createLatestIUQuery(), subMonitor);
		checkForInstall(agent, subMonitor.split(1), queryResult);
	}

	private IStatus checkForInstall(final IProvisioningAgent agent, IProgressMonitor monitor, IQueryResult<IInstallableUnit> toInstall) {

		// configure update operation
		final ProvisioningSession session = new ProvisioningSession(agent);
		InstallOperation operation = new InstallOperation(session, toInstall.toUnmodifiableSet());
		configureUpdate(operation);

		// check for updates, this causes I/O
		final IStatus status = operation.resolveModal(monitor);

		// failed to find updates (inform user and exit)
		if (status.getCode() == UpdateOperation.STATUS_NOTHING_TO_UPDATE) {
			return Status.CANCEL_STATUS;
		}

		// run installation
		final ProvisioningJob provisioningJob = operation.getProvisioningJob(monitor);

		// updates cannot run from within Eclipse IDE!!!
		if (provisioningJob == null) {
			System.err.println("Trying to update from the Eclipse IDE? This won't work!");
			return Status.CANCEL_STATUS;
		}

		provisioningJob.schedule();
		return Status.OK_STATUS;

	}

	private ProfileChangeOperation configureUpdate(final ProfileChangeOperation operation) {

		// set location of artifact and metadata repo
		operation.getProvisioningContext().setArtifactRepositories(new URI[] { location });
		operation.getProvisioningContext().setMetadataRepositories(new URI[] { location });
		return operation;
	}

}
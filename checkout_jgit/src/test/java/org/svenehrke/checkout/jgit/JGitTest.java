package org.svenehrke.checkout.jgit;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.Test;

import java.io.File;

public class JGitTest {


	@Test
	public void test1() throws Exception {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();

		String remoteUrl = "https://github.com/canoo/open-dolphin-gdt.git";
		String localRepoLocation = "build/gitrepo/open-dolphin-gdt.git";

		Repository repository = builder.setWorkTree(new File("build/gitrepo")).build();
		Git git = new Git(repository);
		CloneCommand clone = git.cloneRepository();
		clone.setBare(false);
		clone.setCloneAllBranches(true);
		clone.setDirectory(new File(localRepoLocation)).setURI(remoteUrl);
		clone.call();

	}
}

package codes.showme.pinecone.cdp.code.analysis.gitlab;

import org.gitlab4j.api.models.Diff;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class GitLabDiffWrapperTest {

    @Test
    public void buildDiffObj() {
        GitLabDiffWrapper gitLabDiffWrapper = new GitLabDiffWrapper("commit-id", "default", "repoId");
        Diff diff = new Diff();
        diff.setDiff("@@ -1,8 +1,12 @@\n" +
                "+// Basic hello world example\n" +
                "+\n" +
                " #include <stdio.h>\n" +
                " \n" +
                "-int main()\n" +
                "+int main(int argc, char *argv[])\n" +
                " {\n" +
                "-    printf(\"Hello World\\n\");\n" +
                "-\n" +
                "+    printf(\"Hello Gabriel\\n\");\n" +
                "+    \n" +
                "+    int i = 700;\n" +
                "+    printf(\"i = %i\\n\", i);\n" +
                "     return 0;\n" +
                "-}\n" +
                "\\ No newline at end of file\n" +
                "+}");
        diff.setAMode("100644");
        diff.setBMode("100644");
        diff.setDeletedFile(false);
        diff.setNewFile(false);
        diff.setNewPath("cdp/src/main/java/codes/showme/T.java");
        diff.setOldPath("cdp/src/main/java/codes/showme/T.java");
        diff.setRenamedFile(false);
        codes.showme.pinecone.cdp.domain.commit.Diff diff1 = gitLabDiffWrapper.buildDiffObj(diff);
        assertEquals("commit-id", diff1.getCommitId());
        assertEquals(2, diff1.getDeletedLines());
        assertEquals(6, diff1.getAddedLines());
        assertEquals(2, diff1.getChangedLines());



    }

}
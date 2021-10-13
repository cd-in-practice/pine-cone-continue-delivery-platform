package codes.showme.pinecone.cdp.domain.commit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiffTest {


    @Test
    public void add1LineDelete1Line() {
        Diff diff = new Diff();
        diff.setDiffContent("--- a/a.txt\n" +
                "+++ b/a.txt\n" +
                "@@ -1,8 +1,8 @@\n" +
                " a\n" +
                "+b\n" +
                " c\n" +
                " 1\n" +
                " d\n" +
                " 3\n" +
                " f\n" +
                "-g\n" +
                " 4");
        diff.setNewFile(false);
        diff.setDeletedFile(false);
        diff.setRenamedFile(false);
        diff.analysis();
        assertEquals(1, diff.getDeletedLines());
        assertEquals(0, diff.getChangedLines());
        assertEquals(1, diff.getAddedLines());
    }

    @Test
    public void renameFile() {
        Diff diff = new Diff();
        diff.setDiffContent("");
        diff.setNewFile(false);
        diff.setDeletedFile(false);
        diff.setRenamedFile(true);
        diff.analysis();
        assertEquals(0, diff.getAddedLines());
        assertEquals(0, diff.getDeletedLines());
        assertEquals(0, diff.getChangedLines());
    }

    @Test
    public void modifyMultiLines() {
        Diff diff = new Diff();
        diff.setDiffContent("--- a/a.txt\n" +
                "+++ b/a.txt\n" +
                "@@ -1,8 +1,9 @@\n" +
                " a\n" +
                " b\n" +
                " c\n" +
                "-1\n" +
                "+11\n" +
                " d\n" +
                "-3\n" +
                "-f\n" +
                "+31\n" +
                " 4\n" +
                "+new1\n" +
                "+new2");
        diff.setNewFile(false);
        diff.setDeletedFile(false);
        diff.setRenamedFile(false);
        diff.analysis();
        assertEquals(1, diff.getDeletedLines());
        assertEquals(2, diff.getChangedLines());
        assertEquals(2, diff.getAddedLines());
    }

    @Test
    public void modify1line() {
        Diff diff = new Diff();
        diff.setDiffContent("--- a/a.txt\n" +
                "+++ b/a.txt\n" +
                "@@ -3,7 +3,7 @@ bb\n" +
                " c\n" +
                " 11\n" +
                " d\n" +
                "-31\n" +
                "+32\n" +
                " 5\n" +
                " new1\n" +
                " new2");
        diff.setNewFile(false);
        diff.setDeletedFile(false);
        diff.setRenamedFile(false);
        diff.analysis();
        assertEquals(0, diff.getAddedLines());
        assertEquals(0, diff.getDeletedLines());
        assertEquals(1, diff.getChangedLines());
    }

    @Test
    public void delete1Line() {
        Diff diff = new Diff();
        diff.setDiffContent("--- a/c.txt\n" +
                "+++ /dev/null\n" +
                "@@ -1,4 +0,0 @@\n" +
                "-b1\n" +
                "-3\n" +
                "-b3\n" +
                "-b4");
        diff.setNewFile(false);
        diff.setDeletedFile(true);
        diff.setRenamedFile(false);
        diff.analysis();

        assertEquals(0, diff.getAddedLines());
        assertEquals(4, diff.getDeletedLines());
        assertEquals(0, diff.getChangedLines());
    }

    @Test
    public void testNewFile() {
        Diff diff = new Diff();
        diff.setNewPath("a.txt");
        diff.setOldPath("a.txt");
        diff.setDiffContent("--- /dev/null\n" +
                "+++ b/a.txt\n" +
                "@@ -0,0 +1,7 @@\n" +
                "+a\n" +
                "+b\n" +
                "+c\n" +
                "+d\n" +
                "+e\n" +
                "+f\n" +
                "+g\n" +
                "\\ No newline at end of file");
        diff.setNewFile(true);
        diff.setDeletedFile(false);
        diff.setRenamedFile(false);
        diff.analysis();
        assertEquals(7, diff.getAddedLines());
        assertEquals(0, diff.getDeletedLines());
        assertEquals(0, diff.getChangedLines());
    }


}

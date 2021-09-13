package codes.showme.pinecone.cdp.domain.artifact;

import org.junit.Test;
import notion.api.v1.NotionClient;
import notion.api.v1.model.databases.Databases;
import static org.junit.Assert.*;

public class ArtifactNotionRepositoryImplTest {

    @Test
    public void name() {
        String notion_secure = System.getenv("NOTION_SECURE");
        try (NotionClient client = new NotionClient("")) {
            Databases databases = client.listDatabases();
        }
    }
}
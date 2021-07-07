package codes.showme.pinecone.cdp.domain.artifact;

import codes.showme.pinecone.cdp.domain.artifact.repository.ArtifactRepository;
import codes.showme.pinecone.cdp.tech.common.idgenerator.IdGeneratorMemoryImpl;
import codes.showme.pinecone.cdp.techcommon.idgenerator.IdGenerator;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceFactory;
import codes.showme.pinecone.cdp.techcommon.ioc.InstanceProvider;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.Serializable;

public class DockerImageArtifactTest {

    @Test
    public void newInstanceTest() {
        InstanceProvider instanceProvider = Mockito.mock(InstanceProvider.class);
        IdGeneratorMemoryImpl idGeneratorMemory = new IdGeneratorMemoryImpl();
        Mockito.when(instanceProvider.getInstance(IdGenerator.class)).thenReturn(idGeneratorMemory);
        Mockito.when(instanceProvider.getInstance(ArtifactRepository.class)).thenReturn(new ArtifactMockRepositoryImpl());
        InstanceFactory.setInstanceProvider(instanceProvider);
        DockerImageArtifact dockerImageArtifact = new DockerImageArtifact();
        Serializable result = dockerImageArtifact.save();
        Assert.assertNotNull(result);

    }
}
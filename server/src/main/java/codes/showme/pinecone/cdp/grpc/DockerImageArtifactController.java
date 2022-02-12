package codes.showme.pinecone.cdp.grpc;

import code.showme.pinecone.cdp.artifact.DockerImageArtifactGrpc;
import code.showme.pinecone.cdp.artifact.DockerImageArtifactOuterClass;
import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.DockerImageArtifactCoordinate;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class DockerImageArtifactController extends DockerImageArtifactGrpc.DockerImageArtifactImplBase {
    @Override
    public void push(DockerImageArtifactOuterClass.DockerImageArtifactRequest request, StreamObserver<DockerImageArtifactOuterClass.DockerImageArtifactReply> responseObserver) {
        try {
            Artifact artifact = convert(request);
            artifact.save();
            responseObserver.onNext(DockerImageArtifactOuterClass.DockerImageArtifactReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            // TODO specific error
            responseObserver.onError(e);
        }
    }

    private Artifact convert(DockerImageArtifactOuterClass.DockerImageArtifactRequest request) {
        Artifact result = new Artifact();
        result.setAppId(request.getAppId());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setArtifactVersion(request.getArtifactVersion());
        result.setBuildNumber(request.getBuildNumber());
        result.setNamespace(request.getNamespace());

        DockerImageArtifactCoordinate dockerImageArtifactCoordinate = new DockerImageArtifactCoordinate();
        dockerImageArtifactCoordinate.setRepo(request.getRepo());
        dockerImageArtifactCoordinate.setTag(request.getTag());
        result.setCoordinate(dockerImageArtifactCoordinate);
        return result;
    }
}

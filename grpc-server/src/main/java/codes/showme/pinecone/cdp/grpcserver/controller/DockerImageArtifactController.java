package codes.showme.pinecone.cdp.grpcserver.controller;

import code.showme.pinecone.cdp.artifact.DockerImageArtifactGrpc;
import code.showme.pinecone.cdp.artifact.DockerImageArtifactOuterClass;
import codes.showme.pinecone.cdp.domain.artifact.DockerImageArtifact;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class DockerImageArtifactController extends DockerImageArtifactGrpc.DockerImageArtifactImplBase {
    @Override
    public void push(DockerImageArtifactOuterClass.DockerImageArtifactRequest request, StreamObserver<DockerImageArtifactOuterClass.DockerImageArtifactReply> responseObserver) {
        try {
            DockerImageArtifact artifact = convert(request);
            artifact.save();
            responseObserver.onNext(DockerImageArtifactOuterClass.DockerImageArtifactReply.newBuilder().setId(artifact.getId()).build());
            responseObserver.onCompleted();
        }catch (Exception e){
            // TODO specific error
            responseObserver.onError(e);
        }
    }

    private DockerImageArtifact convert(DockerImageArtifactOuterClass.DockerImageArtifactRequest request) {
        DockerImageArtifact result = new DockerImageArtifact();
        result.setRepo(request.getRepo());
        result.setTag(request.getTag());
        result.setAppId(request.getAppId());
        result.setPipelineId(request.getPipelineId());
        result.setArtifactVersion(request.getArtifactVersion());
        result.setBuildNumber(request.getBuildNumber());
        result.setNamespace(request.getNamespace());
        return result;
    }
}

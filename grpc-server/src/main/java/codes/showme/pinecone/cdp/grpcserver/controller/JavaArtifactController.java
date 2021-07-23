package codes.showme.pinecone.cdp.grpcserver.controller;

import code.showme.pinecone.cdp.artifact.JavaArtifactGrpc;
import code.showme.pinecone.cdp.artifact.JavaArtifactOuterClass;
import codes.showme.pinecone.cdp.domain.artifact.JavaArtifact;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class JavaArtifactController extends JavaArtifactGrpc.JavaArtifactImplBase {
    @Override
    public void push(JavaArtifactOuterClass.JavaArtifactRequest request, StreamObserver<JavaArtifactOuterClass.JavaArtifactReply> responseObserver) {
        try {
            JavaArtifact artifact = convert(request);
            artifact.save();
            responseObserver.onNext(JavaArtifactOuterClass.JavaArtifactReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            // TODO specific error
            responseObserver.onError(e);
        }
    }

    private JavaArtifact convert(JavaArtifactOuterClass.JavaArtifactRequest request) {
        JavaArtifact result = new JavaArtifact();
        result.setAppId(request.getAppId());
        result.setArtifactVersion(request.getArtifactVersion());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setPipelineHistoryUrl(request.getPipelineHistoryUrl());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setBuildNumber(request.getBuildNumber());
        result.setNamespace(request.getNamespace());

        result.setRepoId(request.getRepoId());
        result.setJavaArtifactVersion(request.getJavaArtifactVersion());
        result.setGroupId(request.getGroupId());
        result.setArtifactId(request.getArtifactId());
        return result;
    }
}

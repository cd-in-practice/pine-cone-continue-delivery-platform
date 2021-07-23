package codes.showme.pinecone.cdp.grpcserver.controller;

import code.showme.pinecone.cdp.artifact.TarArtifactGrpc;
import code.showme.pinecone.cdp.artifact.TarArtifactOuterClass;
import code.showme.pinecone.cdp.artifact.TarArtifactGrpc;
import codes.showme.pinecone.cdp.domain.artifact.TarArtifact;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class TarArtifactController extends TarArtifactGrpc.TarArtifactImplBase {
    @Override
    public void push(TarArtifactOuterClass.TarArtifactRequest request, StreamObserver<TarArtifactOuterClass.TarArtifactReply> responseObserver) {
        try {
            TarArtifact artifact = convert(request);
            artifact.save();
            responseObserver.onNext(TarArtifactOuterClass.TarArtifactReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            // TODO specific error
            responseObserver.onError(e);
        }
    }

    private TarArtifact convert(TarArtifactOuterClass.TarArtifactRequest request) {
        TarArtifact result = new TarArtifact();
        result.setAppId(request.getAppId());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setPipelineHistoryUrl(request.getPipelineHistoryUrl());
        result.setArtifactVersion(request.getArtifactVersion());
        result.setBuildNumber(request.getBuildNumber());
        result.setNamespace(request.getNamespace());

        result.setUrl(request.getUrl());
        return result;
    }
}

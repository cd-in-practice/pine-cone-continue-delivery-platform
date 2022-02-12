package codes.showme.pinecone.cdp.grpc;

import code.showme.pinecone.cdp.artifact.TarArtifactGrpc;
import code.showme.pinecone.cdp.artifact.TarArtifactOuterClass;
import codes.showme.pinecone.cdp.domain.artifact.Artifact;
import codes.showme.pinecone.cdp.domain.artifact.TarArtifactCoordinate;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class TarArtifactController extends TarArtifactGrpc.TarArtifactImplBase {
    @Override
    public void push(TarArtifactOuterClass.TarArtifactRequest request, StreamObserver<TarArtifactOuterClass.TarArtifactReply> responseObserver) {
        try {
            Artifact artifact = convert(request);
            artifact.save();
            responseObserver.onNext(TarArtifactOuterClass.TarArtifactReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            // TODO specific error
            responseObserver.onError(e);
        }
    }

    private Artifact convert(TarArtifactOuterClass.TarArtifactRequest request) {
        Artifact result = new Artifact();
        result.setAppId(request.getAppId());
        result.setPipelineHistoryId(request.getPipelineHistoryId());
        result.setArtifactVersion(request.getArtifactVersion());
        result.setBuildNumber(request.getBuildNumber());
        result.setNamespace(request.getNamespace());
        TarArtifactCoordinate tarArtifactCoordinate = new TarArtifactCoordinate();
        tarArtifactCoordinate.setUrl(request.getUrl());
        result.setCoordinate(tarArtifactCoordinate);
        return result;
    }
}

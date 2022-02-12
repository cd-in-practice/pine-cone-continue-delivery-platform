package codes.showme.pinecone.cdp.grpc;

import code.showme.pinecone.cdp.code.CodeAnalysisGrpc;
import code.showme.pinecone.cdp.code.CodeAnalysisOuterClass;
import codes.showme.pinecone.cdp.code.analysis.domain.SyncGitLabCommitsEvent;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class CodeAnalysisController extends CodeAnalysisGrpc.CodeAnalysisImplBase {

    @Override
    public void syncGitLabCommits(CodeAnalysisOuterClass.SyncGitLabCommitsRequest request, StreamObserver<CodeAnalysisOuterClass.SyncGitLabCommitsReply> responseObserver) {

        try {
//            SyncGitLabCommitsEvent event = new SyncGitLabCommitsEvent(request.getNamespace(), request.getGitlabServerUrl(), request.getGitlabProject(), request.getToken());
//            event.save();
            responseObserver.onNext(CodeAnalysisOuterClass.SyncGitLabCommitsReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(e);
        }

    }

    @Override
    public void syncGitLabDiffs(CodeAnalysisOuterClass.SyncGitLabDiffsRequest request, StreamObserver<CodeAnalysisOuterClass.SyncGitLabDiffsReply> responseObserver) {
        try {



            responseObserver.onNext(CodeAnalysisOuterClass.SyncGitLabDiffsReply.newBuilder().setReceived(true).build());
            responseObserver.onCompleted();
        }catch (Exception e){
            responseObserver.onError(e);
        }
    }
}

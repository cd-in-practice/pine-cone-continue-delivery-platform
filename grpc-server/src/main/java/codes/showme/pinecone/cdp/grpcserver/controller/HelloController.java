package codes.showme.pinecone.cdp.grpcserver.controller;

import io.grpc.examples.GreeterGrpc;
import io.grpc.examples.Greeting;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HelloController extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(Greeting.HelloRequest request, StreamObserver<Greeting.HelloReply> responseObserver) {
        super.sayHello(request, responseObserver);
    }
}

package com.pay.user.module.adapter.in;

import com.pay.user.module.port.in.CreateUserUseCase;
import com.pay.user.module.port.in.dto.CreateUserRequest;
import com.pay.user.module.port.in.dto.CreateUserResponse;
import com.user.grpc.CreateUserGrpc;
import com.user.grpc.CreateUserGrpcRequest;
import com.user.grpc.CreateUserGrpcResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CreateUserGrpcAdapter extends CreateUserGrpc.CreateUserImplBase {

    private final CreateUserUseCase port;

    @Override
    public void createUser(CreateUserGrpcRequest request, io.grpc.stub.StreamObserver<com.user.grpc.CreateUserGrpcResponse> responseObserver) {
        CreateUserResponse result = port.createUser(new CreateUserRequest(request.getEmail(), request.getFullName()));
        CreateUserGrpcResponse response = CreateUserGrpcResponse.newBuilder()
                .setEmail(result.email())
                .setId(result.userId().toString())
                .setLedgerAccountId(result.ledgerAccountId())
                .setFullName(result.fullName()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}

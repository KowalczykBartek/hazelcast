/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import javax.annotation.Nullable;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Submits the task to a member for execution. Member is provided with its uuid.
 */
@Generated("c4b01960f4395398c0d3e63533171afb")
public final class ScheduledExecutorSubmitToMemberCodec {
    //hex: 0x1A0300
    public static final int REQUEST_MESSAGE_TYPE = 1704704;
    //hex: 0x1A0301
    public static final int RESPONSE_MESSAGE_TYPE = 1704705;
    private static final int REQUEST_MEMBER_UUID_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_TYPE_FIELD_OFFSET = REQUEST_MEMBER_UUID_FIELD_OFFSET + UUID_SIZE_IN_BYTES;
    private static final int REQUEST_INITIAL_DELAY_IN_MILLIS_FIELD_OFFSET = REQUEST_TYPE_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;
    private static final int REQUEST_PERIOD_IN_MILLIS_FIELD_OFFSET = REQUEST_INITIAL_DELAY_IN_MILLIS_FIELD_OFFSET + LONG_SIZE_IN_BYTES;
    private static final int REQUEST_INITIAL_FRAME_SIZE = REQUEST_PERIOD_IN_MILLIS_FIELD_OFFSET + LONG_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;

    private ScheduledExecutorSubmitToMemberCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * The name of the scheduler.
         */
        public java.lang.String schedulerName;

        /**
         * The UUID of the member where the task will get scheduled.
         */
        public java.util.UUID memberUuid;

        /**
         * type of schedule logic, values 0 for SINGLE_RUN, 1 for AT_FIXED_RATE
         */
        public byte type;

        /**
         * The name of the task
         */
        public java.lang.String taskName;

        /**
         * Name The name of the task
         */
        public com.hazelcast.internal.serialization.Data task;

        /**
         * initial delay in milliseconds
         */
        public long initialDelayInMillis;

        /**
         * period between each run in milliseconds
         */
        public long periodInMillis;
    }

    public static ClientMessage encodeRequest(java.lang.String schedulerName, java.util.UUID memberUuid, byte type, java.lang.String taskName, com.hazelcast.internal.serialization.Data task, long initialDelayInMillis, long periodInMillis) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(true);
        clientMessage.setOperationName("ScheduledExecutor.SubmitToMember");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeUUID(initialFrame.content, REQUEST_MEMBER_UUID_FIELD_OFFSET, memberUuid);
        encodeByte(initialFrame.content, REQUEST_TYPE_FIELD_OFFSET, type);
        encodeLong(initialFrame.content, REQUEST_INITIAL_DELAY_IN_MILLIS_FIELD_OFFSET, initialDelayInMillis);
        encodeLong(initialFrame.content, REQUEST_PERIOD_IN_MILLIS_FIELD_OFFSET, periodInMillis);
        clientMessage.add(initialFrame);
        StringCodec.encode(clientMessage, schedulerName);
        StringCodec.encode(clientMessage, taskName);
        DataCodec.encode(clientMessage, task);
        return clientMessage;
    }

    public static ScheduledExecutorSubmitToMemberCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        request.memberUuid = decodeUUID(initialFrame.content, REQUEST_MEMBER_UUID_FIELD_OFFSET);
        request.type = decodeByte(initialFrame.content, REQUEST_TYPE_FIELD_OFFSET);
        request.initialDelayInMillis = decodeLong(initialFrame.content, REQUEST_INITIAL_DELAY_IN_MILLIS_FIELD_OFFSET);
        request.periodInMillis = decodeLong(initialFrame.content, REQUEST_PERIOD_IN_MILLIS_FIELD_OFFSET);
        request.schedulerName = StringCodec.decode(iterator);
        request.taskName = StringCodec.decode(iterator);
        request.task = DataCodec.decode(iterator);
        return request;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {
    }

    public static ClientMessage encodeResponse() {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        clientMessage.add(initialFrame);

        return clientMessage;
    }

    public static ScheduledExecutorSubmitToMemberCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        //empty initial frame
        iterator.next();
        return response;
    }

}

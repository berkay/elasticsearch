/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.groovy.client

import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequest
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse
import org.elasticsearch.action.admin.cluster.node.shutdown.NodesShutdownRequest
import org.elasticsearch.action.admin.cluster.node.shutdown.NodesShutdownResponse
import org.elasticsearch.action.admin.cluster.state.ClusterStateRequest
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse
import org.elasticsearch.client.ClusterAdminClient
import org.elasticsearch.client.internal.InternalClient
import org.elasticsearch.groovy.client.action.GActionFuture

/**
 * @author kimchy (shay.banon)
 */
class GClusterAdminClient {

    private final GClient gClient

    private final InternalClient internalClient;

    final ClusterAdminClient clusterAdminClient;

    def GClusterAdminClient(gClient) {
        this.gClient = gClient;
        this.internalClient = gClient.client;
        this.clusterAdminClient = internalClient.admin().cluster();
    }

    // HEALTH

    GActionFuture<ClusterHealthResponse> health(Closure c) {
        ClusterHealthRequest request = new ClusterHealthRequest()
        c.setDelegate request
        c.resolveStrategy = gClient.resolveStrategy
        c.call()
        health(request)
    }

    GActionFuture<ClusterHealthResponse> health(ClusterHealthRequest request) {
        GActionFuture<ClusterHealthResponse> future = new GActionFuture<ClusterHealthResponse>(internalClient.threadPool(), request);
        clusterAdminClient.health(request, future)
        return future
    }

    void status(ClusterHealthRequest request, ActionListener<ClusterHealthResponse> listener) {
        clusterAdminClient.health(request, listener)
    }

    // STATE

    GActionFuture<ClusterStateResponse> state(Closure c) {
        ClusterStateRequest request = new ClusterStateRequest()
        c.setDelegate request
        c.resolveStrategy = gClient.resolveStrategy
        c.call()
        state(request)
    }

    GActionFuture<ClusterStateResponse> state(ClusterStateRequest request) {
        GActionFuture<ClusterStateResponse> future = new GActionFuture<ClusterStateResponse>(internalClient.threadPool(), request);
        clusterAdminClient.state(request, future)
        return future
    }

    void state(ClusterStateRequest request, ActionListener<ClusterStateResponse> listener) {
        clusterAdminClient.state(request, listener)
    }

    // NODES INFO

    GActionFuture<NodesInfoResponse> nodesInfo(Closure c) {
        NodesInfoRequest request = new NodesInfoRequest()
        c.setDelegate request
        c.resolveStrategy = gClient.resolveStrategy
        c.call()
        nodesInfo(request)
    }

    GActionFuture<NodesInfoResponse> nodesInfo(NodesInfoRequest request) {
        GActionFuture<NodesInfoResponse> future = new GActionFuture<NodesInfoResponse>(internalClient.threadPool(), request);
        clusterAdminClient.nodesInfo(request, future)
        return future
    }

    void nodesInfo(NodesInfoRequest request, ActionListener<NodesInfoResponse> listener) {
        clusterAdminClient.nodesInfo(request, listener)
    }

    // NODES INFO

    GActionFuture<NodesShutdownResponse> nodesShutdown(Closure c) {
        NodesShutdownRequest request = new NodesShutdownRequest()
        c.setDelegate request
        c.resolveStrategy = gClient.resolveStrategy
        c.call()
        nodesShutdown(request)
    }

    GActionFuture<NodesShutdownResponse> nodesShutdown(NodesShutdownRequest request) {
        GActionFuture<NodesShutdownResponse> future = new GActionFuture<NodesShutdownResponse>(internalClient.threadPool(), request);
        clusterAdminClient.nodesShutdown(request, future)
        return future
    }

    void nodesShutdown(NodesShutdownRequest request, ActionListener<NodesShutdownResponse> listener) {
        clusterAdminClient.nodesShutdown(request, listener)
    }
}

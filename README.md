# SimpleDynamo
Implemented a simple dynamo style key-value storage with replication, partitioning and failure handling.

Used a file-based content provider to create server and client threads, to open sockets, and to respond to the incoming requests.

Maintained linearizability using Quorum replication.

Handled multiple node (AVDs) crashes, recovery, failures, and re-joins seamlessly while the other nodes were operating concurrently.

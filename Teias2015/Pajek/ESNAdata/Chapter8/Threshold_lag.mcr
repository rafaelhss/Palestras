NETBEGIN 2
CLUBEGIN 2
PERBEGIN 1
CLSBEGIN 1
HIEBEGIN 1
VECBEGIN 1
NETPARAM 1
CLUPARAM 1

Msg
Msg Compute threshold lags.
Msg Make SURE that you select the diffusion network and adoption time partition FIRST.
V 1 MVEC 1 (38)
N 2 ETOAINC 1 1 1 DEL (38)
N 2 BATOEMIN 2 (38)
N 2 REMEDG 2 (38)
V 2 VECMAX 2 1 [0] FALSE (38)
V 3 SUBV 1 2 (38)
V 4 IDVEC  (N)
V 5 SUBV 3 4 (38)
C 2 DEGC 2 [0] (38)
C 3 BIN 2 [1,100000] (38)
V 7 MVEC 3
V 8 MULV 5 7 (38)
Msg
Msg Ready! The last vector contains the threshold lags of all vertices.

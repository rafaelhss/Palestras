NETBEGIN 2
CLUBEGIN 2
PERBEGIN 1
CLSBEGIN 1
HIEBEGIN 1
VECBEGIN 1
NETPARAM 1
CLUPARAM 1

Msg Macro to compute the exposure of vertices at a particular time.
Msg Make sure that the diffusion network and the adoption time partition are selected!
Msg 
Msg Symmetrize network and remove multiple lines and loops.
N 2 ATOE 1 (38)
N 2 SIMPLMIN 2 (38)
N 2 DLOOPS 2 (38)
Msg
Msg Binarize adoption time partition: select first adoption time and time of exposure.
C 2 BIN 1 [?0,?2] (38)
V 1 MVEC 2 (38)
Msg
Msg Compute the number of adopters in the neighborhood of each vertex.
V 2 VECSUM 2 1 [0] FALSE (38)
Msg Compute size of neighborhood with input degree.
C 3 DEGC 2 [0] (38)
V 4 MVEC 3 (38)
Msg Divide the number of adopters in the neighborhood by the size of the neighborhood.
V 5 DIVV 2 4 (38)

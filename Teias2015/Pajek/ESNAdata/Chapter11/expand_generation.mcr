NETBEGIN 2
CLUBEGIN 2
PERBEGIN 1
CLSBEGIN 1
HIEBEGIN 1
VECBEGIN 1
NETPARAM 1
CLUPARAM 1

Msg  
Msg Extend selected individuals with spouses, children of parents,
Msg and parents of children.
Msg Ore graph or P-graph and a binarized partition with the 
Msg selected birth cohort must be selected.
N 2 REMARC 1 (14)
V 1 MVEC 1 (14)
V 2 VECMAX 2 1 [0] TRUE (14)
N 3 REMEDG 1 (14)
V 3 VECMAX 3 2 [1] TRUE (14)
V 4 SUBV 3 2 (14)
V 5 VECMAX 3 4 [0] TRUE (14)
V 6 SUBV 5 4 (14)
V 7 SUBV 6 2 (14)
C 2 MAKETRUNCPAR 7 (14)
C 3 BIN 2 [1,1000] (14)
V 8 MVEC 3 (14)
V 9 ADDV 8 2 (14)
V 10 VECMAX 3 2 [0] TRUE (14)
V 11 SUBV 10 9 (14)
V 12 VECMAX 2 11 [0] TRUE (14)
V 13 VECMAX 3 12 [1] TRUE (14)
V 14 SUBV 13 12 (14)
V 15 ADDV 14 9 (14)
C 4 MAKETRUNCPAR 15 (14)
C 5 BIN 4 [1,1000] (14)
Msg 
Msg Ready. The new partition contains expanded generation in class 1.

package com.github.mstraub.inheritance;

public interface Weighter {
}

interface NodeWeighter extends Weighter {}
interface EdgeWeighter extends Weighter {}
interface CompoundWeighter {}

class MyNodeWeighter implements NodeWeighter {}
class MyEdgeWeighter implements EdgeWeighter {}


// implement serveral interfaces is OK
class CompoundableNodeWeighter implements NodeWeighter, CompoundWeighter {}

// interface diamond of death is OK
interface MyBastardInterface extends NodeWeighter, CompoundWeighter {}
// class diamond of death is NOT OK
//class MyBastardWeighter extends MyNodeWeighter, MyEdgeWeighter {}
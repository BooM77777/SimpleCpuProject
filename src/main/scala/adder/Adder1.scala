package adder

import chisel3._

class Adder1 extends Module {
    val io = IO (new Bundle {
        val i0  = Input(UInt(1.W))
        val i1  = Input(UInt(1.W))
        val cin = Input(UInt(1.W))
        val out  = Output(UInt(1.W))
        val cout = Output(UInt(1.W))
    })
    io.out := io.i0 ^ io.i1 ^ io.cin
    io.cout := (io.i0 & io.i1) | (io.i0 & io.cin) | (io.i1 & io.cin)
}
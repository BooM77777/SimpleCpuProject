package adder

import chisel3._
import chisel3.util._

class Adder16 extends Module {
    val io = IO (new Bundle {
        val i0  = Input(UInt(16.W))
        val i1  = Input(UInt(16.W))
        val cin = Input(UInt(1.W))
        val out  = Output(UInt(16.W))
        val cout = Output(UInt(1.W))
    })

    val adder0 = Module(new Adder8)
    adder0.io.i0 := io.i0(7,0)
    adder0.io.i1 := io.i1(7,0)
    adder0.io.cin := io.cin

    val adder1 = Module(new Adder8)
    adder1.io.i0 := io.i0(15,8)
    adder1.io.i1 := io.i1(15,8)
    adder1.io.cin := adder0.io.cout

    io.out := Cat(adder1.io.out, adder0.io.out)
    io.cout := adder1.io.cout
}

class Adder32 extends Module {
    val io = IO (new Bundle {
        val i0  = Input(UInt(32.W))
        val i1  = Input(UInt(32.W))
        val cin = Input(UInt(1.W))
        val out  = Output(UInt(32.W))
        val cout = Output(UInt(1.W))
    })

    val adder0 = Module(new Adder16)
    adder0.io.i0 := io.i0(15,0)
    adder0.io.i1 := io.i1(15,0)
    adder0.io.cin := io.cin

    val adder1 = Module(new Adder16)
    adder1.io.i0 := io.i0(31,16)
    adder1.io.i1 := io.i1(31,16)
    adder1.io.cin := adder0.io.cout

    io.out := Cat(adder1.io.out, adder0.io.out)
    io.cout := adder1.io.cout
}
package adder

import chisel3._
import chisel3.util._

class Adder2 extends Module {
    val io = IO (new Bundle {
        val i0  = Input(UInt(2.W))
        val i1  = Input(UInt(2.W))
        val cin = Input(UInt(1.W))
        val out  = Output(UInt(2.W))
        val cout = Output(UInt(1.W))
    })

    val adder0 = Module(new Adder1)
    adder0.io.i0 := io.i0(0)
    adder0.io.i1 := io.i1(0)
    adder0.io.cin := io.cin

    val adder1 = Module(new Adder1)
    adder1.io.i0 := io.i0(1)
    adder1.io.i1 := io.i1(1)
    adder1.io.cin := adder0.io.cout

    io.out := Cat(adder1.io.out, adder0.io.out)
    io.cout := adder1.io.cout
}

class Adder4 extends Module {
    val io = IO (new Bundle {
        val i0  = Input(UInt(4.W))
        val i1  = Input(UInt(4.W))
        val cin = Input(UInt(1.W))
        val out  = Output(UInt(4.W))
        val cout = Output(UInt(1.W))
    })

    val adder0 = Module(new Adder2)
    adder0.io.i0 := io.i0(1,0)
    adder0.io.i1 := io.i1(1,0)
    adder0.io.cin := io.cin

    val adder1 = Module(new Adder2)
    adder1.io.i0 := io.i0(3,2)
    adder1.io.i1 := io.i1(3,2)
    adder1.io.cin := adder0.io.cout

    io.out := Cat(adder1.io.out, adder0.io.out)
    io.cout := adder1.io.cout
}

class Adder8 extends Module {
    val io = IO (new Bundle {
        val i0  = Input(UInt(8.W))
        val i1  = Input(UInt(8.W))
        val cin = Input(UInt(1.W))
        val out  = Output(UInt(8.W))
        val cout = Output(UInt(1.W))
    })

    val adder0 = Module(new Adder4)
    adder0.io.i0 := io.i0(3,0)
    adder0.io.i1 := io.i1(3,0)
    adder0.io.cin := io.cin

    val adder1 = Module(new Adder4)
    adder1.io.i0 := io.i0(7,4)
    adder1.io.i1 := io.i1(7,4)
    adder1.io.cin := adder0.io.cout

    io.out := Cat(adder1.io.out, adder0.io.out)
    io.cout := adder1.io.cout
}
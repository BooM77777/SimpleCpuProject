package utils

import chisel3._
import chisel3.util._

class Dmux2() extends Module {
    val io = IO (new Bundle {
        val in = Input(UInt(1.W))
        val out = Vec(2, Output(UInt(1.W)))
    })
    io.out(0) := ~io.in
    io.out(1) := io.in
}

class Dmux4() extends Module {
    val io = IO (new Bundle {
        val in = Input(UInt(2.W))
        val out = Vec(4, Output(UInt(1.W)))
    })

    val dmux = Module(new Dmux2)
    dmux.io.in := io.in(0)

    for (i <- 0 to 1) {
        io.out(i) := ~io.in(1) & dmux.io.out(i)
        io.out(i + 2) := io.in(1) & dmux.io.out(i)
    }
}

class Dmux8() extends Module {
    val io = IO (new Bundle {
        val in = Input(UInt(3.W))
        val out = Vec(8, Output(UInt(1.W)))
    })

    val dmux = Module(new Dmux4)
    dmux.io.in := io.in(1,0)

    for (i <- 0 to 3) {
        io.out(i) := ~io.in(2) & dmux.io.out(i)
        io.out(i + 4) := io.in(2) & dmux.io.out(i)
    }
}

class Dmux16() extends Module {
    val io = IO (new Bundle {
        val in = Input(UInt(4.W))
        val out = Vec(16, Output(UInt(1.W)))
    })

    val dmux = Module(new Dmux8)
    dmux.io.in := io.in(2,0)

    for (i <- 0 to 7) {
        io.out(i) := ~io.in(3) & dmux.io.out(i)
        io.out(i + 8) := io.in(3) & dmux.io.out(i)
    }
}

class Dmux32() extends Module {
    val io = IO (new Bundle {
        val in = Input(UInt(5.W))
        val out = Vec(32, Output(UInt(1.W)))
    })

    val dmux = Module(new Dmux16)
    dmux.io.in := io.in(3,0)

    for (i <- 0 to 15) {
        io.out(i) := ~io.in(4) & dmux.io.out(i)
        io.out(i + 16) := io.in(4) & dmux.io.out(i)
    }
}

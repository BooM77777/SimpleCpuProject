package util

import chisel3._
import chisel3.util._

class Mux2(val width : Int) extends Module {
    val io = IO (new Bundle {
        val in = Vec(2, Input(UInt(width.W)))
        val sel = Input(UInt(1.W))
        val out = Output(UInt(width.W))
    })
    val selAfterFill = Fill(width, io.sel)
    io.out := io.in(0) & ~selAfterFill | io.in(1) & selAfterFill
}

class Mux4(val width : Int) extends Module {
    val io = IO (new Bundle {
        val in = Vec(4, Input(UInt(width.W)))
        val sel = Input(UInt(2.W))
        val out = Output(UInt(width.W))
    })

    val mux0 = Module(new Mux2(width))
    mux0.io.in(0) := io.in(0)
    mux0.io.in(1) := io.in(1)
    mux0.io.sel := io.sel(0)

    val mux1 = Module(new Mux2(width))
    mux1.io.in(0) := io.in(2)
    mux1.io.in(1) := io.in(3)
    mux1.io.sel := io.sel(0)

    val mux2 = Module(new Mux2(width))
    mux2.io.in(0) := mux0.io.out
    mux2.io.in(1) := mux1.io.out
    mux2.io.sel := io.sel(1)
    io.out := mux2.io.out
}

class Mux8(val width : Int) extends Module {
    val io = IO (new Bundle {
        val in = Vec(8, Input(UInt(width.W)))
        val sel = Input(UInt(3.W))
        val out = Output(UInt(width.W))
    })

    val mux0 = Module(new Mux4(width))
    mux0.io.in(0) := io.in(0)
    mux0.io.in(1) := io.in(1)
    mux0.io.in(2) := io.in(2)
    mux0.io.in(3) := io.in(3)
    mux0.io.sel := io.sel(1,0)

    val mux1 = Module(new Mux4(width))
    mux1.io.in(0) := io.in(4)
    mux1.io.in(1) := io.in(5)
    mux1.io.in(2) := io.in(6)
    mux1.io.in(3) := io.in(7)
    mux1.io.sel := io.sel(1,0)

    val mux2 = Module(new Mux2(width))
    mux2.io.in(0) := mux0.io.out
    mux2.io.in(1) := mux1.io.out
    mux2.io.sel := io.sel(2)

    io.out := mux2.io.out
}

class Mux16(val width : Int) extends Module {
    val io = IO (new Bundle {
        val in = Vec(16, Input(UInt(width.W)))
        val sel = Input(UInt(4.W))
        val out = Output(UInt(width.W))
    })

    val mux0 = Module(new Mux8(width))
    mux0.io.in(0) := io.in(0x0)
    mux0.io.in(1) := io.in(0x1)
    mux0.io.in(2) := io.in(0x2)
    mux0.io.in(3) := io.in(0x3)
    mux0.io.in(4) := io.in(0x4)
    mux0.io.in(5) := io.in(0x5)
    mux0.io.in(6) := io.in(0x6)
    mux0.io.in(7) := io.in(0x7)
    mux0.io.sel := io.sel(2,0)

    val mux1 = Module(new Mux8(width))
    mux1.io.in(0) := io.in(0x8)
    mux1.io.in(1) := io.in(0x9)
    mux1.io.in(2) := io.in(0xa)
    mux1.io.in(3) := io.in(0xb)
    mux1.io.in(4) := io.in(0xc)
    mux1.io.in(5) := io.in(0xd)
    mux1.io.in(6) := io.in(0xe)
    mux1.io.in(7) := io.in(0xf)
    mux1.io.sel := io.sel(2,0)

    val mux2 = Module(new Mux2(width))
    mux2.io.in(0) := mux0.io.out
    mux2.io.in(1) := mux1.io.out
    mux2.io.sel := io.sel(3)

    io.out := mux2.io.out
}

class Mux32(val width : Int) extends Module {
    val io = IO (new Bundle {
        val in = Vec(32, Input(UInt(width.W)))
        val sel = Input(UInt(5.W))
        val out = Output(UInt(width.W))
    })

    val mux0 = Module(new Mux16(width))
    mux0.io.in(0x0) := io.in(0x00)
    mux0.io.in(0x1) := io.in(0x01)
    mux0.io.in(0x2) := io.in(0x02)
    mux0.io.in(0x3) := io.in(0x03)
    mux0.io.in(0x4) := io.in(0x04)
    mux0.io.in(0x5) := io.in(0x05)
    mux0.io.in(0x6) := io.in(0x06)
    mux0.io.in(0x7) := io.in(0x07)
    mux0.io.in(0x8) := io.in(0x08)
    mux0.io.in(0x9) := io.in(0x09)
    mux0.io.in(0xa) := io.in(0x0a)
    mux0.io.in(0xb) := io.in(0x0b)
    mux0.io.in(0xc) := io.in(0x0c)
    mux0.io.in(0xd) := io.in(0x0d)
    mux0.io.in(0xe) := io.in(0x0e)
    mux0.io.in(0xf) := io.in(0x0f)
    mux0.io.sel := io.sel(3,0)

    val mux1 = Module(new Mux16(width))
    mux1.io.in(0x0) := io.in(0x10)
    mux1.io.in(0x1) := io.in(0x11)
    mux1.io.in(0x2) := io.in(0x12)
    mux1.io.in(0x3) := io.in(0x13)
    mux1.io.in(0x4) := io.in(0x14)
    mux1.io.in(0x5) := io.in(0x15)
    mux1.io.in(0x6) := io.in(0x16)
    mux1.io.in(0x7) := io.in(0x17)
    mux1.io.in(0x8) := io.in(0x18)
    mux1.io.in(0x9) := io.in(0x19)
    mux1.io.in(0xa) := io.in(0x1a)
    mux1.io.in(0xb) := io.in(0x1b)
    mux1.io.in(0xc) := io.in(0x1c)
    mux1.io.in(0xd) := io.in(0x1d)
    mux1.io.in(0xe) := io.in(0x1e)
    mux1.io.in(0xf) := io.in(0x1f)
    mux1.io.sel := io.sel(3,0)

    val mux2 = Module(new Mux2(width))
    mux2.io.in(0) := mux0.io.out
    mux2.io.in(1) := mux1.io.out
    mux2.io.sel := io.sel(4)

    io.out := mux2.io.out
}
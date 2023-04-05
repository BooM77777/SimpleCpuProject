package register

import chisel3._
import utils.Mux32

class RegisterList extends Module {
    val io = IO ( new Bundle {
        val idx = Input(UInt(5.W))
        val rw  = Input(UInt(1.W))
        val in  = Input(UInt(64.W))
        val out = Output(UInt(64.W))
    })

    val regList = Reg(Vec(32, UInt(0.W)))

    val mux = Module(new Mux32(64))
    for (i <- 0 to 31) {
        mux.io.in(i) := regList(i)
    }
    mux.io.sel := io.idx

    io.out := mux.io.out
}
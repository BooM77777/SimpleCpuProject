package util

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class Mux2Spec extends AnyFlatSpec with ChiselScalatestTester {

    "Mux2" should "pass" in {
        test(new Mux2(64)) { dut =>

            dut.io.in(0)poke(65536)
            dut.io.in(1)poke(4096)
            dut.io.sel.poke(0)
            dut.io.out.expect(65536)
            
            dut.io.in(0)poke(65536)
            dut.io.in(1)poke(4096)
            dut.io.sel.poke(1)
            dut.io.out.expect(4096)
        }
    }

    "Mux4" should "pass" in {
        test(new Mux4(64)) { dut =>
            
            dut.io.in(0).poke(2)
            dut.io.in(1).poke(4)
            dut.io.in(2).poke(8)
            dut.io.in(3).poke(16)
            dut.io.sel.poke(0)
            dut.io.out.expect(2)

            dut.io.in(0).poke(2)
            dut.io.in(1).poke(4)
            dut.io.in(2).poke(8)
            dut.io.in(3).poke(16)
            dut.io.sel.poke(1)
            dut.io.out.expect(4)

            dut.io.in(0).poke(2)
            dut.io.in(1).poke(4)
            dut.io.in(2).poke(8)
            dut.io.in(3).poke(16)
            dut.io.sel.poke(2)
            dut.io.out.expect(8)

            dut.io.in(0).poke(2)
            dut.io.in(1).poke(4)
            dut.io.in(2).poke(8)
            dut.io.in(3).poke(16)
            dut.io.sel.poke(3)
            dut.io.out.expect(16)
        }
    }

    "Mux32" should "pass" in {
        test(new Mux32(64)) { dut =>
            for (i <- 0 to 31) {
                for (i <- 0 to 31) {
                    dut.io.in(i).poke(i)
                }
                dut.io.sel.poke(i)
                dut.io.out.expect(i)
            }
        }
    }

}
package utils

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DmuxSpec extends AnyFlatSpec with ChiselScalatestTester {

    "Dmux32" should "pass" in {
        test(new Dmux32) { dut =>
            for (i <- 0 to 31) {
                dut.io.in.poke(i)
                for (j <- 0 to 31) {
                    if (i == j) {
                        dut.io.out(j).expect(1)
                    } else {
                        dut.io.out(j).expect(0)
                    }
                }
            } 
        }
    }
}
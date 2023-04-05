package register

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class RegisterSpec extends AnyFlatSpec with ChiselScalatestTester {

    "Register" should "pass" in {
        test(new RegisterList) { dut =>
            for (i <- 0 to 31) {
                dut.io.idx.poke(i)
                dut.io.out.expect(0)
            }
        }
    }
}
//******************************************************************************
// Copyright (c) 2017 - 2018, The Regents of the University of California (Regents).
// All Rights Reserved. See LICENSE and LICENSE.SiFive for license details.
//------------------------------------------------------------------------------
// Author: Christopher Celio
//------------------------------------------------------------------------------

package boom.system

import chisel3._

import freechips.rocketchip.config.Parameters
import freechips.rocketchip.diplomacy.LazyModule
import freechips.rocketchip.devices.debug.Debug

/**
 * A test harness to wrap around the BOOM system
 */
class TestHarness()(implicit p: Parameters) extends Module
{
  val io = IO(new Bundle
  {
    val success = Output(Bool())
  })

  println("\n\nBuilding TestHarness for an ExampleBoomSystem.\n")

  val dut = Module(LazyModule(new ExampleBoomSystem).module)
  dut.reset := reset.toBool | dut.debug.ndreset

  dut.dontTouchPorts()
  dut.tieOffInterrupts()
  dut.connectSimAXIMem()
  dut.connectSimAXIMMIO()
  dut.l2_frontend_bus_axi4.foreach( q => q := DontCare ) // Overridden in next line
  dut.l2_frontend_bus_axi4.foreach(_.tieoff)

  Debug.connectDebug(dut.debug, clock, reset.toBool, io.success)
}

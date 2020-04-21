//******************************************************************************
// Copyright (c) 2018 - 2018, The Regents of the University of California (Regents).
// All Rights Reserved. See LICENSE and LICENSE.SiFive for license details.
//------------------------------------------------------------------------------
// Author: Christopher Celio
//------------------------------------------------------------------------------

package boom.system

/**
 * Any BOOM-specific tests can go here to override default rocket-chip behavior.
 */
object BoomTestSuites
{
  import freechips.rocketchip.system.DefaultTestSuites._

  // We do not currently support breakpoints, so override the rv64mi and its descendents.
  val rv64miNames = rv32miNames + "access"
  val rv64mi = new freechips.rocketchip.system.AssemblyTestSuite("rv64mi", rv64miNames)(_)
  val rv64i  = List(rv64ui, rv64si, rv64mi)
  val rv64pi = List(rv64ui, rv64mi)

  val rv64uc = new freechips.rocketchip.system.AssemblyTestSuite("rv64uc", rv64ucNames)(_)
}

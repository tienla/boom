#!/bin/bash

# create the riscv tools binaries from riscv-boom/boom-template with rocket-chip hash given by riscv-boom

# turn echo on and error on earliest command
set -x
set -e

if [ ! -d "$HOME/riscv-tools-install" ]; then

    cd $HOME/boom-template

    # build the tools
    ./scripts/build-tools.sh
fi

# Versions

## Introduction

This directory contains all of the working versions of PopLibrary. If a new version is not working, old versions can be found here.

## Standards

All software versions must be stored in their own folder following the appropriate naming conventions. The contents of
the folder are as follows:
* Software Executable
* Patch Notes

## Naming Convention

For this project, we are following something similar to the .NET standard for software versions:

(Major version).(Minor version).(Revision number).(Build number)

If we were to release a version of PopLibrary today (9/7/2016), the version number might look like the following:

*0.3.23.0407*

Since we have not even gotten to a beta version yet, we will go ahead and say the major version is 0. As far as minor versions, 
we have added support for barcode scanners, a SQLite database, and upcdatabase.org lookups. These are not exactly minor changes in
terms of programming, but they come together to create a final first major build of the product (phase 1). The revision number is
basically random in this example, but it would reflect all of the changes to the master branch since the last minor version. The build
number is just a reflection of the months since the project started and the day of the release.

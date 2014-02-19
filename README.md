[![Build Status](https://api.travis-ci.org/stormpath/stormpath-scala.png?branch=master)](https://travis-ci.org/stormpath/stormpath-scala)

# Scala plugin for Stormpath #

Copyright &copy; 2014 Stormpath, Inc. and contributors. This project is open-source via the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0).  

The `stormpath-scala` plugin allows [Scala](http://www.scala-lang.org/) and [Play Framework](http://www.playframework.com/) Applications to use the [Stormpath](http://www.stormpath.com) User Management & Authentication service for authentication needs.

This plugin simplifies the integration of the [Stormpath Java SDK](https://github.com/stormpath/stormpath-sdk-java) with Scala and Play applications, providing simple access to Stormpath's user account support, authentication, account registration and password reset workflows, password security and more -with little coding on your part. It provides asynchronous operation while providing a Scala-friendly API.

Usage documentation [is in the wiki](https://github.com/stormpath/stormpath-scala/wiki).

### Build Instructions ###

This project requires scala 2.10 and [sbt 0.13.1](http://www.scala-sbt.org/) to build.  Run the following from a command prompt:

`sbt publish`

## Change Log

### 0.1.0

- First release providing only an authentication API and executor context.
- Stormpath SDK dependency version 0.9.2

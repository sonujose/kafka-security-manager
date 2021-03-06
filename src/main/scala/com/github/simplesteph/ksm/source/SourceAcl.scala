package com.github.simplesteph.ksm.source

import com.github.simplesteph.ksm.parser.AclParser
import com.typesafe.config.Config

trait SourceAcl {

  /**
    * Config Prefix for configuring this module
    */
  val CONFIG_PREFIX: String

  /**
    * internal config definition for the module
    */
  def configure(config: Config)

  /**
    * Refresh the current view on the external source of truth for Acl
    * Ideally this function is smart and does not pull the entire external Acl at every iteration
    * Return `None` if the Source Acls have not changed (usually using metadata).
    * Return `Some(x)` if the Acls have changed. `x` represents the parsing and parsing errors if any
    * Note: the first call to this function should never return `None`.
    *
    * Kafka Security Manager will not update Acls in Kafka until there are no errors in the result
    * @return
    */
  def refresh(aclParser: AclParser): Option[SourceAclResult]

  /**
    * Close all the necessary underlying objects or connections belonging to this instance
    */
  def close()
}

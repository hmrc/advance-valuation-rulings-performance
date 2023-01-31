/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.ars

import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.ars.Requests._

trait ArsRequests {
  self: PerformanceTestRunner with ServicesConfiguration =>

  private val baseUrl: String = baseUrlFor("ars-frontend")

  val payload = Map(
    "value[0]" -> "option1",
    "value[1]" -> "option2",
    "value[2]" -> "option3",
    "value[3]" -> "option4",
    "value[4]" -> "option5",
    "value[5]" -> "option6",
    "value[6]" -> "option7",
    "value[7]" -> "option8",
  )

  setup("initial-journey", "test ") withRequests(
    getPage("account home",true, s"$baseUrl/advance-valuation-ruling/accountHome"),
    getPage("starter checklist",true, s"$baseUrl/advance-valuation-ruling/requiredInformation?csrfToken="+"${csrfToken}"),
    postPage("starter checklist",s"$baseUrl/advance-valuation-ruling/requiredInformation", s"$baseUrl/advance-valuation-ruling/importGoods", payload),
    getPage("About the goods", true,s"$baseUrl/advance-valuation-ruling/importGoods")

    )

}

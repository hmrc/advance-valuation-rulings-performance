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

import uk.gov.hmrc.perftests.ars.Requests._

trait ArsRequests {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  private val baseUrl  = Configuration.arsUrl

  def navigateToAaccountHome =
    getPage("account home", true, s"$baseUrl/advance-valuation-ruling/accountHome")

  def navigateToStarterChecklist = getPage(
    "starter checklist",
    true,
    s"$baseUrl/advance-valuation-ruling/requiredInformation?csrfToken=" + "${csrfToken}"
  )

  def submitStarterChecklist(allTicked: Boolean) = {
    val allBoxesTickedPayload = Map(
      "value[0]" -> "option1",
      "value[1]" -> "option2",
      "value[2]" -> "option3",
      "value[3]" -> "option4",
      "value[4]" -> "option5",
      "value[5]" -> "option6",
      "value[6]" -> "option7",
      "value[7]" -> "option8",
    )
    postPage(
      "starter checklist",
      s"$baseUrl/advance-valuation-ruling/requiredInformation",
      s"$baseUrl/advance-valuation-ruling/importGoods",
      if (allTicked) allBoxesTickedPayload else Map.empty[String, String]
    )
  }

  def navigateToPlanningToImportGoods =
    getPage(
      "Are you planning to import goods to the UK",
      true,
      s"$baseUrl/advance-valuation-ruling/importGoods"
    )

  def submitPlanningToImportGoods(answer: Boolean) = postPage(
    "Are you planning to import goods to the UK",
    s"$baseUrl/advance-valuation-ruling/importGoods",
    s"$baseUrl/advance-valuation-ruling/valuationMethod",
    answer.toPayload
  )

  def navigateToPublicInformationNotice =
    getPage(
      "publicInformationNotice",
      true,
      s"$baseUrl/advance-valuation-ruling/publicInformationNotice"
    )

  def navigateToContactAboutYourApp =
    getPage(
      "Contact about your app",
      true,
      s"$baseUrl/advance-valuation-ruling/contactPage?csrfToken=" + "${csrfToken}"
    )

  def navigateToCheckRegisteredDetails =
    getPage(
      "Check registered details",
      true,
      s"$baseUrl/advance-valuation-ruling/checkRegisteredDetails?csrfToken=" + "${csrfToken}"
    )

  // Leaving these here for now as they are the next steps for the journey and need to be updated
  //   postPage("?applicationContactDetails",s"$baseUrl/advance-valuation-ruling/applicationContactDetails", s"$baseUrl/advance-valuation-ruling/valuationMethod", payload),
  //   postPage("?valuationMethod",s"$baseUrl/advance-valuation-ruling/valuationMethod", s"$baseUrl/advance-valuation-ruling/nameOfGoods", payload),
  //   postPage("?nameOfGoods",s"$baseUrl/advance-valuation-ruling/nameOfGoods", s"$baseUrl/advance-valuation-ruling/hasCommodityCode", payload),
  //   postPage("?hasCommodityCode",s"$baseUrl/advance-valuation-ruling/hasCommodityCode", s"$baseUrl/advance-valuation-ruling/commodityCode", payload),
  //   postPage("?commodityCode",s"$baseUrl/advance-valuation-ruling/commodityCode", s"$baseUrl/advance-valuation-ruling/whatCountryAreGoodsFrom", payload),
  //   postPage("?whatCountryAreGoodsFrom",s"$baseUrl/advance-valuation-ruling/whatCountryAreGoodsFrom", s"$baseUrl/advance-valuation-ruling/areGoodsShippedDirectly", payload),
  //   postPage("?areGoodsShippedDirectly",s"$baseUrl/advance-valuation-ruling/areGoodsShippedDirectly", s"$baseUrl/advance-valuation-ruling/describeTheGoods", payload),
  //   postPage("?describeTheGoods",s"$baseUrl/advance-valuation-ruling/describeTheGoods", s"$baseUrl/advance-valuation-ruling/howAreTheGoodsMade", payload),
  //   postPage("?howAreTheGoodsMade",s"$baseUrl/advance-valuation-ruling/howAreTheGoodsMade", s"$baseUrl/advance-valuation-ruling/hasConfidentialInformation", payload),
  //   postPage("?hasConfidentialInformation",s"$baseUrl/advance-valuation-ruling/hasConfidentialInformation", s"$baseUrl/advance-valuation-ruling/confidentialInformation", payload),
  //   postPage("?confidentialInformation",s"$baseUrl/advance-valuation-ruling/confidentialInformation", s"$baseUrl/advance-valuation-ruling/doYouWantToUploadDocuments", payload),
  //   postPage("?doYouWantToUploadDocuments",s"$baseUrl/advance-valuation-ruling/doYouWantToUploadDocuments", s"$baseUrl/advance-valuation-ruling", payload),
}

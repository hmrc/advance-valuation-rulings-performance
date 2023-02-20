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

  private val baseUrl = Configuration.arsUrl

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

  def navigateToCheckRegisteredDetails(answer: Boolean) =
    getPage(
      "Check registered details",
      true,
      s"$baseUrl/advance-valuation-ruling/checkRegisteredDetails?csrfToken=" + "${csrfToken}"
    )

  def navigateToProvideContactDetails(answer: Boolean) =
    getPage(
      "Enter Contact details",
      true,
      s"$baseUrl/advance-valuation-ruling/applicationContactDetails"
    )

  def submitProvideContactDetails(enterText: Boolean) = {

    val enterTextAllBoxes = Map(
      "name" -> "test",
      "email" -> "test@gmail.com",
      "phone" -> "12345678",
    )

    postPage(
      "Provide Contact Details",
      s"$baseUrl/advance-valuation-ruling/applicationContactDetails",
      s"$baseUrl/advance-valuation-ruling/valuationMethod",
      enterTextAllBoxes
    )
  }

  def navigateToMethodNamePage(answer: Boolean) =
    getPage(
      "Method Name Page",
      true,
      s"$baseUrl/advance-valuation-ruling/valuationMethod"
    )

  def selectMethodNamePage(selectAnyOneMethod: Boolean) = {
    val selectMethod = Map(
      "value" -> "method1",
    )
    postPage(
      "selectMethod",
      s"$baseUrl/advance-valuation-ruling/valuationMethod",
      s"$baseUrl/advance-valuation-ruling/whyTransactionValueOfSimilarGoods",
      if (selectAnyOneMethod) selectMethod else Map.empty[String, String]
    )
  }

  def navigateWhyNotSelectedMethod1Page = {
    getPage(
      "Why Not Selected Method1 Page",
      true,
      s"$baseUrl/advance-valuation-ruling/whyTransactionValueOfSimilarGoods"
    )
  }
    def enterReasonNotSelectedMethod1(enterText: Boolean) = {

      val enterText = Map(
        "value" -> "test",
      )

      postPage(
        "Enter reason for Not selecting Method 1",
        s"$baseUrl/advance-valuation-ruling/whyTransactionValueOfSimilarGoods",
        s"$baseUrl/advance-valuation-ruling/haveYouUsedMethodOneInPast",
        enterText
      )
    }

      def naviagteHaveYouUsedMethodInLast90daysPage = {
        getPage(
          "Have you selected method 1 in last 90 days",
          true,
          s"$baseUrl/advance-valuation-ruling/haveYouUsedMethodOneInPast"
        )
      }

      def submitYouUsedMethodInPastPage(answer: Boolean) = postPage(
        "Select yes on method 1 not selected  in last 90 days",
        s"$baseUrl/advance-valuation-ruling/haveYouUsedMethodOneInPast",
        s"$baseUrl/advance-valuation-ruling/nameOfGoods",
        answer.toPayload
      )

  def navigateNameOfTheGoodsPage = {
    getPage(
      "Name Of The Goods Page",
      true,
      s"$baseUrl/advance-valuation-ruling/nameOfGoods"
    )
  }
  def enterNameofTheGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text",
    )

    postPage(
      "Enter Nam of The Goods",
      s"$baseUrl/advance-valuation-ruling/nameOfGoods",
      s"$baseUrl/advance-valuation-ruling/hasCommodityCode",
      enterText
    )
  }

  def navigateFoundCommodityCodePage = {
    getPage(
      "Found Commodity Code Page",
      true,
      s"$baseUrl/advance-valuation-ruling/hasCommodityCode"
    )
  }

  def submitFoundCommodityCode(answer: Boolean) = {
    postPage(
      "Click Yes or No in  Found Commodity Code",
      s"$baseUrl/advance-valuation-ruling/hasCommodityCode",
      s"$baseUrl/advance-valuation-ruling/commodityCode",
      answer.toPayload
    )
  }

  def navigateCommodityCodePage = {
    getPage(
      "Navigate Commodity Code Page",
      true,
      s"$baseUrl/advance-valuation-ruling/commodityCode"
    )
  }
  def enterCommodityCode(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "1234",
    )

    postPage(
      "Enter Commodity Code",
      s"$baseUrl/advance-valuation-ruling/commodityCode",
      s"$baseUrl/advance-valuation-ruling/whatCountryAreGoodsFrom",
      enterText
    )
  }

  def navigateWhichCountryGoodsComingFromPage = {
    getPage(
      "What Country Goods Coming From",
      true,
      s"$baseUrl/advance-valuation-ruling/whatCountryAreGoodsFrom"
    )
  }
  def enterCountryNameinGoodComingPage(enterText: Boolean) = {

    val enterText = Map(
      "answer" -> "India",

    )
    postPage(
      "Enter Country Name in Goods Coming Page",
      s"$baseUrl/advance-valuation-ruling/whatCountryAreGoodsFrom",
      s"$baseUrl/advance-valuation-ruling/areGoodsShippedDirectly",
      enterText
    )
  }
  def naviagteToShiipedFromOrginCountryToUK =
    getPage(
      "Naviagte To Shiiped From Orgin Country To UK",
      true,
      s"$baseUrl/advance-valuation-ruling/areGoodsShippedDirectly"
    )

  def submitToShippedFromOrginCountryToUK(answer: Boolean) = postPage(
    "Select Yes in Shipped From Orgin Country To UK",
    s"$baseUrl/advance-valuation-ruling/areGoodsShippedDirectly",
    s"$baseUrl/advance-valuation-ruling/describeTheGoods",
    answer.toPayload
  )


  def navigateDescribeTheGoodsPage = {
    getPage(
      "navigateDescribeTheGoodsPage",
      true,
      s"$baseUrl/advance-valuation-ruling/describeTheGoods"
    )
  }
  def enterTheDescriptionofTheGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text",
    )

    postPage(
      "Describe  The Goods",
      s"$baseUrl/advance-valuation-ruling/describeTheGoods",
      s"$baseUrl/advance-valuation-ruling/howAreTheGoodsMade",
      enterText
    )
  }


  def navigateTheGoodsMadePage = {
    getPage(
      "navigateGoodsMadePage",
      true,
      s"$baseUrl/advance-valuation-ruling/howAreTheGoodsMade"
    )
  }
  def enterTheGoodsMadeInfo(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text",
    )

    postPage(
      "enterTheGoodsMadeInfo",
      s"$baseUrl/advance-valuation-ruling/howAreTheGoodsMade",
      s"$baseUrl/advance-valuation-ruling/hasConfidentialInformation",
      enterText
    )
  }


  def navigateToConfidentialInfoPage =
    getPage(
      "Navigate To Add Confidential Info Page",
      true,
      s"$baseUrl/advance-valuation-ruling/hasConfidentialInformation"
    )

  def submitYesInConfidentialInfoPage(answer: Boolean) = postPage(
    "Select Yes in Confidential Page",
    s"$baseUrl/advance-valuation-ruling/hasConfidentialInformation",
    s"$baseUrl/advance-valuation-ruling/confidentialInformation",
    answer.toPayload
  )


  def navigateToEnterConfidentialInfoPage = {
    getPage(
      "Navigate To Confidential  info Page",
      true,
      s"$baseUrl/advance-valuation-ruling/confidentialInformation"
    )
  }
  def enterTheConfidentialInfo(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text",
    )

    postPage(
      "enter The Goods Confidential Info",
      s"$baseUrl/advance-valuation-ruling/confidentialInformation",
      s"$baseUrl/advance-valuation-ruling/doYouWantToUploadDocuments",
      enterText
    )
  }

  def navigateToUploadSupportingDocsPage =
    getPage(
      "Navigate To Upload Supporting Docs Page",
      true,
      s"$baseUrl/advance-valuation-ruling/doYouWantToUploadDocuments"
    )

  def submitNoInUploadSupportingDocsPage(answer: Boolean) = postPage(
    "Select No Upload Supporting Docs Page",
    s"$baseUrl/advance-valuation-ruling/doYouWantToUploadDocuments",
    s"$baseUrl/advance-valuation-ruling/uploadSupportingDocuments",
    answer.toPayload
  )
}



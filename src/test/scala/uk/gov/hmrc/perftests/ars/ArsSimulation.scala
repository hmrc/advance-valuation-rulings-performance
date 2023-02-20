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
import uk.gov.hmrc.perftests.ars.AuthRequests._

class ArsSimulation extends PerformanceTestRunner with ServicesConfiguration with ArsRequests {
  setup(
    "registration-login",
    "Log in to auth"
  ).withRequests(
    navigateToAuthWizard,
    submitAuthWizard
  )

  setup("initial-journey", "test ")
    .withActions(
      navigateToAaccountHome,

      navigateToStarterChecklist,

      submitStarterChecklist(allTicked = true),

      navigateToPlanningToImportGoods,
      submitPlanningToImportGoods(true),

      navigateToPublicInformationNotice,

      navigateToContactAboutYourApp,

      navigateToCheckRegisteredDetails(answer=true),

      navigateToProvideContactDetails(true),
      submitProvideContactDetails(true),

      navigateToMethodNamePage(true),
      selectMethodNamePage(true),

      navigateWhyNotSelectedMethod1Page,
      enterReasonNotSelectedMethod1(true),

      naviagteHaveYouUsedMethodInLast90daysPage,
      submitYouUsedMethodInPastPage(true),

      navigateNameOfTheGoodsPage,
      enterNameofTheGoods(true),

      navigateFoundCommodityCodePage,
      submitFoundCommodityCode(true),

      navigateCommodityCodePage,
      enterCommodityCode(true),

      navigateWhichCountryGoodsComingFromPage,
      enterCountryNameinGoodComingPage(true),

      naviagteToShiipedFromOrginCountryToUK,
      submitToShippedFromOrginCountryToUK(true),

      navigateDescribeTheGoodsPage,
      enterTheDescriptionofTheGoods(true),

      navigateTheGoodsMadePage,
      enterTheGoodsMadeInfo(true),

      navigateToConfidentialInfoPage,
      submitYesInConfidentialInfoPage(true),

      navigateToEnterConfidentialInfoPage,
      enterTheConfidentialInfo(true),

      navigateToUploadSupportingDocsPage,
      submitNoInUploadSupportingDocsPage(false),
    )

  runSimulation()
}

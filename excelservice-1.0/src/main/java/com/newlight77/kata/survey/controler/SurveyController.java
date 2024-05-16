package com.newlight77.kata.survey.controler;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.ExportCampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/survey")
public class SurveyController {

    private final ExportCampaignService exportCampaignService;

    @PostMapping(value = "/create")
    public void createSurvey(@RequestBody final Survey survey) {
        exportCampaignService.creerSurvey(survey);
    }

    @GetMapping(value = "/get")
    public Survey getSurvey(@RequestParam final String id) {
        return exportCampaignService.getSurvey(id);
    }

    @PostMapping(value = "/campaign/create")
    public void createCampaign(@RequestBody final Campaign campaign) {
        exportCampaignService.createCampaign(campaign);
    }

    @GetMapping(value = "/campaign/get")
    public Campaign getCampaign(@RequestParam final String id) {
        return exportCampaignService.getCampaign(id);
    }

    @PostMapping(value = "/campaign/export")
    public void exportCampaign(@RequestParam final String campaignId) {

        final Campaign campaign = exportCampaignService.getCampaign(campaignId);
        final Survey survey = exportCampaignService.getSurvey(campaign.getSurveyId());
        exportCampaignService.sendResults(campaign, survey);
        
    }
}


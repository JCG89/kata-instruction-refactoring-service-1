package com.newlight77.kata.survey.client;

import com.newlight77.kata.survey.config.CompaignConfig;
import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CampaignClient {
    /*
    L'annotation @Service est exclusivement reservée  aux class qui incluent du code business
    Un code business est un code demandé par le Poo
     */

    private final WebClient webClient;
    private final CompaignConfig campaignConfig;

    public CampaignClient(CompaignConfig campaignConfig) {
        this.campaignConfig = campaignConfig;
        webClient = WebClient.builder()
                .baseUrl(campaignConfig.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    public void createSurvey(final Survey survey) {
        webClient.post()
                .uri(campaignConfig.getEndpoint().getSurvey())
                .bodyValue(survey)
                .retrieve();
    }

    public Survey getSurvey(final String id) {
        return webClient.get()
                .uri(campaignConfig.getEndpoint().getSurvey() + "/" + id)
                .retrieve()
                .bodyToMono(Survey.class).block();
    }

    public void createCampaign(final Campaign campaign) {
        webClient.post()
                .uri(campaignConfig.getEndpoint().getCampaign())
                .bodyValue(campaign);
    }

    public Campaign getCampaign(final String id) {
        return webClient.get()
                .uri(campaignConfig.getEndpoint().getCampaign() +"/" + id)
                .retrieve()
                .bodyToMono(Campaign.class).block();
    }
}

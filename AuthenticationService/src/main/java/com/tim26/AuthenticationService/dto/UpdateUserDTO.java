package com.tim26.AuthenticationService.dto;

public class UpdateUserDTO {

    private AgentDTO agentDTO;

    private EndUserDTO endUserDTO;

    private AdminDTO adminDTO;

    public UpdateUserDTO(){}

    public UpdateUserDTO(AgentDTO agentDTO, EndUserDTO endUserDTO, AdminDTO adminDTO) {
        this.agentDTO = agentDTO;
        this.endUserDTO = endUserDTO;
        this.adminDTO = adminDTO;
    }

    public AgentDTO getAgentDTO() {
        return agentDTO;
    }

    public void setAgentDTO(AgentDTO agentDTO) {
        this.agentDTO = agentDTO;
    }

    public EndUserDTO getEndUserDTO() {
        return endUserDTO;
    }

    public void setEndUserDTO(EndUserDTO endUserDTO) {
        this.endUserDTO = endUserDTO;
    }

    public AdminDTO getAdminDTO() {
        return adminDTO;
    }

    public void setAdminDTO(AdminDTO adminDTO) {
        this.adminDTO = adminDTO;
    }
}

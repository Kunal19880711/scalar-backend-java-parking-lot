package controllers;

import models.IssueTicketRequestDTO;
import models.IssueTicketResponseDTO;
import models.Response;

public interface TicketController {
    Response<IssueTicketResponseDTO> createTicket(IssueTicketRequestDTO request);
}

package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

    @GetMapping
    public List<Attachment> getAllAttachmentController() {
        return attachmentService.getAllAttachmentService();
    }

    @GetMapping("/getAttachmentInfo/{id}")
    public Attachment getAttachmentInfoController(@PathVariable Integer id) {
        return attachmentService.getAttachmentInfoService(id);
    }

    @GetMapping("/getAttachmentContent/{id}")
    public void getAttachmentContentController(@PathVariable Integer id, HttpServletResponse response) {
        attachmentService.getAttachmentContentService(id, response);
    }

    @DeleteMapping("/deleteAttachment/{id}")
    public Result deleteAttachmentContent(@PathVariable Integer id) {
        return attachmentService.deleteAttachmentService(id);
    }
}

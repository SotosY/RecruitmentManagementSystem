package com.example.recruitmentsystemproject.Model.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FileResponse {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long fileSize;
}

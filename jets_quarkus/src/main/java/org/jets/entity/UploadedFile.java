package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Transient
    @JsonIgnore
    private File file;

    @JsonIgnore
    @ManyToMany(mappedBy = "uploadedFiles")
    private List<Passenger> passengers = new ArrayList<>();
}
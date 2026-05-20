package org.jets.resource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jets.DTO.FileUploadDTO;
import org.jets.entity.Passenger;
import org.jets.entity.UploadedFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Path("/files")
public class FileResource {

    @PersistenceContext
    EntityManager em;

    private static final String UPLOAD_DIR = "uploads/";

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadFile(
            @QueryParam("passengerId") Long passengerId,
            @MultipartForm FileUploadDTO dto
    ) {

        Passenger passenger = em.find(Passenger.class, passengerId);

        if (passenger == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Passenger not found")
                    .build();
        }

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            if (dto == null || dto.filename == null || dto.file == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Missing filename or file")
                        .build();
            }

            var filePath = Paths.get(UPLOAD_DIR, dto.filename);

            // zapis fajla
            try (InputStream input = dto.file) {
                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFilename(filePath.toString());

            // MANY TO MANY SAFE SYNC
            passenger.getUploadedFiles().add(uploadedFile);

            if (uploadedFile.getPassengers() != null) {
                uploadedFile.getPassengers().add(passenger);
            }

            em.persist(uploadedFile);
            em.merge(passenger);
            em.flush();

            return Response.ok("File uploaded successfully").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("ERROR: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getPassengerFiles(@PathParam("id") Long id) {

        Passenger passenger = em.find(Passenger.class, id);

        if (passenger == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Passenger not found")
                    .build();
        }

        passenger.getUploadedFiles().forEach(f -> {
            f.setPassengers(null);
        });

        return Response.ok(passenger).build();
    }
}
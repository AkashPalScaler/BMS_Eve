package com.scaler.BMS_Eve;

import com.scaler.BMS_Eve.DTOs.*;
import com.scaler.BMS_Eve.controllers.BookingController;
import com.scaler.BMS_Eve.controllers.UserController;
import com.scaler.BMS_Eve.models.*;
import com.scaler.BMS_Eve.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BmsEveApplication implements CommandLineRunner {

	@Autowired
	UserController userController;
	@Autowired
	BookingController bookingController;
	@Autowired
	SeatRepository seatRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
	@Autowired
	private ShowSeatTypeRepository showSeatTypeRepository;
	@Autowired
	private ShowRepository showRepository;

	public static void main(String[] args) {
		SpringApplication.run(BmsEveApplication.class, args);
	}

	void dataSetup(){
		// Add a city
		City city = new City();
		city.setName("Mumbai");
		cityRepository.save(city);

		// Add 1 theatre to the city
		Theatre theatre1 = new Theatre();
		theatre1.setName("Phoenix IMAX");
		theatre1.setCity(city);
		theatreRepository.save(theatre1);

		// Add 1 screen in the theatre
		Screen screen1 = new Screen();
		screen1.setName("Screen 1");
		screen1.setTheatre(theatre1);
		screenRepository.save(screen1);

		// Add 4 seats on the screen
		Seat seat1 = new Seat();seat1.setName("1A");seat1.setType(SeatType.GOLD);
		Seat seat2 = new Seat();seat2.setName("2A");seat2.setType(SeatType.PREMIUM);
		Seat seat3 = new Seat();seat3.setName("3A");seat3.setType(SeatType.GOLD);
		Seat seat4 = new Seat();seat4.setName("4A");seat4.setType(SeatType.PREMIUM);
		seatRepository.saveAll(List.of(seat1, seat2, seat3, seat4));
		screen1.setSeats(List.of(seat1, seat2, seat3, seat4));
		screenRepository.save(screen1);

		theatre1.setScreens(List.of(screen1));
		theatreRepository.save(theatre1);

		city.setTheatres(List.of(theatre1));
		cityRepository.save(city);

		// Create 1 movies
		Movie movie1 = new Movie();
		movie1.setName("obsession");
		movie1.setGenre("Thriller");
		movieRepository.save(movie1);
		// Create 2 shows
		Show show9Am = new Show();
		show9Am.setTheatre(theatre1);
		show9Am.setMovie(movie1);
		show9Am.setScreen(screen1);
		showRepository.save(show9Am);
		List<ShowSeat> showSeats = new ArrayList<>();
		for(Seat seat : screen1.getSeats()){
			ShowSeat showSeat = new ShowSeat();
			showSeat.setShow(show9Am);
			showSeat.setSeat(seat);
			showSeat.setStatus(ShowSeatStatus.AVAILABLE);
			showSeats.add(showSeat);
		}
		showSeatRepository.saveAll(showSeats);

		List<ShowSeatType> showSeatTypes = new ArrayList<>();
		for(SeatType seatType : SeatType.values()){
			ShowSeatType showSeatType = new ShowSeatType();
			showSeatType.setShow(show9Am);
			showSeatType.setSeatType(seatType);
			if(seatType.equals(SeatType.GOLD)){
				showSeatType.setPrice(300);
			}else if(seatType.equals(SeatType.PREMIUM)){
				showSeatType.setPrice(200);
			}
			showSeatTypes.add(showSeatType);
		}
		showSeatTypeRepository.saveAll(showSeatTypes);
		show9Am.setShowSeatList(showSeats);
		show9Am.setShowSeatTypes(showSeatTypes);
		showRepository.save(show9Am);

//		Show show10Am = new Show();
//		show9Am.setTheatre(theatre1);
//		show9Am.setMovie(movie1);
//		show9Am.setScreen(screen1);
		// Create showSeats
		// Create ShowSeatType
	}

	@Override
	public void run(String... args) throws Exception {
//		UserSignUpRequestDTO requestDTO = new UserSignUpRequestDTO();
//		requestDTO.setName("Akash");
//		requestDTO.setPassword("Password");
//		requestDTO.setEmail("akash@email.com");
//		UserSignUpResponseDTO responseDTO = userController.signUp(requestDTO);
//		if(responseDTO.getResponseStatus().equals(ResponseStatus.FAILURE)){
//			System.out.println("Error - " + responseDTO.getMessage());
//		}else{
//			System.out.println("User sign up succesfull " + responseDTO.getUserId());
//		}
//
//		requestDTO.setName("Milan");
//		requestDTO.setPassword("somepassword");
//		requestDTO.setEmail("milan@email.com");
//		responseDTO = userController.signUp(requestDTO);
//		if(responseDTO.getResponseStatus().equals(ResponseStatus.FAILURE)){
//			System.out.println("Error - " + responseDTO.getMessage());
//		}else{
//			System.out.println("User sign up succesfull " + responseDTO.getUserId());
//		}
//		dataSetup();

		BookTicketRequestDTO requestDTO = new BookTicketRequestDTO();
		requestDTO.setUserId(1L);
		requestDTO.setShowId(1L);
		requestDTO.setShowSeatIds(List.of(4L,5L));
		BookTicketResponseDTO responseDTO = bookingController.bookTicket(requestDTO);
		if(responseDTO.getResponseStatus().equals(ResponseStatus.SUCCESS)){
			System.out.println("Booking successful with ID : " + responseDTO.getBookingId() + " and amount " + responseDTO.getAmount());
			System.out.println("Please start the payment process");
		}else{
			System.out.println("Error -" + responseDTO.getMessage());
		}

	}
}

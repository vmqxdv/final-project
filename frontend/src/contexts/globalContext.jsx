import { createContext, useState, useEffect, useContext } from "react";
import axios from "axios";

const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  const [films, setFilms] = useState([]);
  const [genres, setGenres] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [filmsRes, genresRes] = await Promise.all([
          axios.get(import.meta.env.VITE_API_URL_FILMS),
          axios.get(import.meta.env.VITE_API_URL_GENRES),
        ]);
        setFilms(filmsRes.data);
        setGenres(genresRes.data);
      } catch (error) {
        console.error("Errore nel recupero dei dati:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return (
    <GlobalContext.Provider value={{ films, genres, loading }}>
      {children}
    </GlobalContext.Provider>
  );
};

export const useGlobalContext = () => useContext(GlobalContext);
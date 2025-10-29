import { createContext, useState, useEffect, useContext } from "react";
import axios from "axios";

const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  const [films, setFilms] = useState([]);
  const [genres, setGenres] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      axios.get(import.meta.env.VITE_API_URL_FILMS),
      axios.get(import.meta.env.VITE_API_URL_GENRES)
    ])
      .then(([filmsRes, genresRes]) => {
        setFilms(filmsRes.data);
        setGenres(genresRes.data);
      })
      .catch(err => console.error("Errore nel recupero dei dati:", err))
      .finally(() => setLoading(false));
  }, []);

  return (
    <GlobalContext.Provider value={{ films, genres, loading }}>
      {children}
    </GlobalContext.Provider>
  );
};

export const useGlobalContext = () => useContext(GlobalContext);
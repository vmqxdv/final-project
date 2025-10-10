import { useParams } from "react-router-dom";
import { useGlobalContext } from "../contexts/globalContext";
import FilmCard from "../components/FilmCard";

export default function GenreDetail() {
  const { id } = useParams();
  const { genres, films } = useGlobalContext();

  if (!genres || genres.length === 0)
    return <div className="container mt-5 text-center"><p>Caricamento...</p></div>;

  const genre = genres.find(g => g.id === Number(id));
  const genreFilms = films.filter(f => f.genres.some(g => g.id === genre.id));

  return (
    <div className="container mt-5">
      <div className="card shadow-sm border-0">
        <div className="card-body">
          <h2 className="card-title mb-3">{genre.name}</h2>
          <p className="text-muted mb-4">
            Film appartenenti a questo genere:
          </p>
          {genreFilms.length > 0 ? (
            <div className="row row-cols-1 row-cols-md-3 g-4">
              {genreFilms.map(film => (
                <div className="col" key={film.id}>
                  <FilmCard film={film} />
                </div>
              ))}
            </div>
          ) : (
            <p className="text-muted">Nessun film trovato per questo genere.</p>
          )}
        </div>
      </div>
    </div>
  );
};
import FilmCard from '../components/FilmCard';
import { useGlobalContext } from '../contexts/globalContext';

export default function FilmList() {
  const { films } = useGlobalContext();

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Lista Film</h2>
      <div className="row row-cols-1 row-cols-md-3 g-4 mb-5">
        {films.map((film) => (
          <div className="col" key={`${film.id}-${film.name}`}>
            <FilmCard film={film} />
          </div>
        ))}
      </div>
    </div>
  );
}
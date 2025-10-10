import { useParams } from "react-router-dom";
import { useGlobalContext } from '../contexts/globalContext';

export default function FilmDetail() {
  const { films } = useGlobalContext();
  const { id } = useParams();

  if (!films || films.length === 0)
    return <div className="container mt-5 text-center"><p>Caricamento...</p></div>;

  const film = films.find(f => f.id === Number(id));
  const { name, image, durationFormatted, genres } = film;

  return (
    <div className="container mt-5">
      <h2 className="mb-4">{name}</h2>
      <div className="card p-3 shadow-sm">
        <div className="d-flex flex-column flex-md-row align-items-start">
          <img src={image} alt={name} className="img-fluid rounded mb-3 mb-md-0 me-md-4" style={{ maxWidth: '15rem' }} />
          <div>
            <h4 className="mb-3 text-secondary">Dettagli</h4>
            <p><strong>Durata:</strong> {durationFormatted}</p>
            <p><strong>Generi:</strong> {genres.map(g => g.name).join(', ')}</p>
          </div>
        </div>
      </div>
    </div>
  );
};
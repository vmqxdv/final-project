import { useGlobalContext } from '../contexts/GlobalContext';
import { Link } from 'react-router-dom';

export default function GenreList() {
  const { genres } = useGlobalContext();

  return (
    <div className="container mt-5">
      <section>
        <h2 className="mb-4">I nostri generi</h2>
        <table className="table table-striped table-hover align-middle shadow-sm">
          <thead className="table-primary">
            <tr>
              <th>Nome</th>
              <th className="text-center">Azioni</th>
            </tr>
          </thead>
          <tbody>
            {genres.map((genre) => (
              <tr key={`${genre.id}-${genre.name}`}>
                <td>{genre.name}</td>
                <td className="text-center">
                  <Link
                    to={`/genres/${genre.id}`}
                    className="btn btn-sm btn-outline-primary me-2"
                  >
                    Dettagli
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </div>
  );
};